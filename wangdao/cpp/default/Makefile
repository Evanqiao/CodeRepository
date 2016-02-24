.PHONY: all clean
all: default-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

default-test: default.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: default-test
	./default-test
