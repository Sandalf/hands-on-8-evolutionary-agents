package ai.genalgots;

import java.util.Random;

public class Individual {
    private int[] chromosome;
    private double fitness = -1;

    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public Individual(int length) {
        this.chromosome = new int[length];

        for (int i = 0; i < chromosome.length; i++) {
            Random rand = new Random();
            this.chromosome[i] = rand.nextInt((9 - 1) + 1) + 1;
        }
    }

    public int evaluate() {
        int a = getGene(0);
        int b = getGene(1);
        int c = getGene(2);
        int d = getGene(3);
        int e = getGene(4);
        int f = getGene(5);
        return a + (2*b) - (3*c) + d + (4*e) + f;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }
    
    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    public String toString() {
        String output = "";

        for(int gene = 0; gene < this.getChromosomeLength(); gene++) {
            output += this.getGene(gene);
        }

        return output;
    }

    public String equationToString() {
        return getGene(0) + " + 2(" + getGene(1) + ") - 3(" + getGene(2) + ") + " + getGene(3) + " + 4(" + getGene(4) + ") + " + getGene(5) + " = " + this.evaluate();
    }
}
