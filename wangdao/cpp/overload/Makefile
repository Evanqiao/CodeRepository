.PHONY: all clean
all: overload-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

overload-test: overload.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: overload-test
	./overload-test
