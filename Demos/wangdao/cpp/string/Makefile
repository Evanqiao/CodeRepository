.PHONY: all clean
all: string-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

string-test: string.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: string-test
	./string-test
