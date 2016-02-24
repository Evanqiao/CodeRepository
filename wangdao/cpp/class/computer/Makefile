.PHONY: all clean
all: computer-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

computer-test: computer.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: computer-test
	./computer-test
