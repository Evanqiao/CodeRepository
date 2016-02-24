.PHONY: all clean
all: reference-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

reference-test: reference.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: reference-test
	./reference-test
