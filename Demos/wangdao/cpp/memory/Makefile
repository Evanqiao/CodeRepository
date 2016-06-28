.PHONY: all clean
all: memory-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

memory-test: memory.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: memory-test
	./memory-test
