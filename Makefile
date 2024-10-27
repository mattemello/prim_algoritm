all: clear main

clear:
	rm -f *.class src3/*.class src4/*.class

# to execute:
# java Prim "[file name]"
main:
	javac Prim.java

#--------3-----------

all3: clear3 test3 start3

clear3:
	rm -f src3/*.class

test3:
	javac -cp hamcrest-core-1.3.jar:junit-4.12.jar:. src3/PriorityQueueTestRunner.java
	
start3:
	java -cp hamcrest-core-1.3.jar:junit-4.12.jar:. src3/PriorityQueueTestRunner

#--------4-----------

all4: clear4 test4 start4

clear4:
	rm -f src4/*.class

test4:
	javac -cp hamcrest-core-1.3.jar:junit-4.12.jar:. src4/GraphTestRunner.java
	
start4:
	java -cp hamcrest-core-1.3.jar:junit-4.12.jar:. src4/GraphTestRunner

