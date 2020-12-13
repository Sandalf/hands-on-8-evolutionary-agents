# Hands on 8 - Evolutionary Agents

Agente Evolutivo (Jade) , para maximizar la función `f(x) = x^2` en el rango [1, 30]

# How to compile
From root directory run:

```shell
javac -cp lib/jade.jar src/ai/genalgots/*.java -d classes/
```

# How to execute
From root directory run an pass the `x` value as a parameter:
```shell
java -cp lib/jade.jar:classes/ jade.Boot -gui 'maxFunctionAgent:ai.genalgots.MaxFunctionAgent'
```