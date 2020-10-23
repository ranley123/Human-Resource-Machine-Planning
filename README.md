# Human Resource Machine Eprime

## Usage
```
./savilerow-1.8.0-mac/savilerow Game.eprime test.param -run-solver -solver-options "-cpulimit 600" -S2 -O3
```
```
./savilerow-1.8.0-mac/savilerow Game.eprime ../sat-intances/10.param  -run-solver -sat 
```
The reason why SAT solver would perform so much better could be: SAT solver can translate problems and constraints into SAT problems. From CS3105 Artificial Intelligence, we had a coursework about making a WalkSAT to solve sat problems. WalkSAT can handle over 50 variables and 200 clauses. Its strong capability enabled the instanc