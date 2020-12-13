package ai.genalgots;

import java.util.Random;

public class GeneticAlgorithm {
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;
    private int target;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount, int target) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.target = target;
    }

    public Population initPopulation(int chromosomeLength) {
        Population population = new Population(this.populationSize, chromosomeLength);
        return population;
    }

    public double calcFitness(Individual individual) {
        double fitness =  Math.pow(individual.evaluate(), 2);
        int equationResult = individual.evaluate();
        individual.setFitness(100 - Math.abs(equationResult - this.target));

        return fitness;
    }

    public void evalPopulation(Population population) {
        double populationFitness = 0;

        for(Individual individual : population.getIndividuals()) {
           populationFitness += calcFitness(individual);
        }

        population.setPopulationFitness(populationFitness);
    }

    public boolean isTerminationConditionMet(Population population) {
        for(Individual individual : population.getIndividuals()) {
            if (individual.getFitness() == 100) {
                return true;
            }
        }
        return false;
    }

    public Individual selectParent(Population population) {
        Individual individuals[] = population.getIndividuals();

        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;

        double spinWheel = 0;

        for(Individual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }

        return individuals[population.size() - 1];
    }

    public Population crossoverPopulation(Population population) {
        Population newPopulation = new Population(population.size());

        for(int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual parent1 = population.getFittest(populationIndex);

            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                Individual offsping = new Individual(parent1.getChromosomeLength());

                Individual parent2 = selectParent(population);

                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    if (0.5 > Math.random()) {
                        offsping.setGene(geneIndex, parent1.getGene(geneIndex));
                    } else {
                        offsping.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }

                newPopulation.setIndividual(populationIndex, offsping);
            } else {
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        return newPopulation;
    }

    public Population mutatePopulation(Population population) {
        Population newPopulation = new Population(this.populationSize);
        Random rand = new Random();

        for (int populationIndex = 0; populationIndex < population.size(); populationIndex ++) {
            Individual individual = population.getFittest(populationIndex);

            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {            
                if (populationIndex > this.elitismCount) {
                    if (this.mutationRate > Math.random()) {                        
                        int newGene =  rand.nextInt((9 - 1) + 1) + 1;
                        individual.setGene(geneIndex, newGene);
                    }
                }
            }
            newPopulation.setIndividual(populationIndex, individual);
        }

        return newPopulation;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public int getElitismCount() {
        return elitismCount;
    }

    public void setElitismCount(int elitismCount) {
        this.elitismCount = elitismCount;
    }    
}
