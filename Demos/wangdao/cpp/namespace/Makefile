.PHONY: all clean
all: namespace-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

namespace-test: namespace.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: namespace-test
	./namespace-test
