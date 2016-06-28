.PHONY: all clean
all: point-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

point-test: point.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: point-test
	./point-test
