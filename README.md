# Hands on 8 - Evolutionary Agents

Agente Evolutivo  en Jade para  para solucionar la ecuación: `a + 2b - 3c + d + 4e + f = 30`


# How to compile
From root directory run:

```shell
javac -cp lib/jade.jar src/ai/genalgots/*.java -d classes/
```

# How to execute
From root directory run an pass the `x` value as a parameter:
```shell
java -cp lib/jade.jar:classes/ jade.Boot -gui 'solveEquationAgent:ai.genalgots.SolveEquationAgent'
```