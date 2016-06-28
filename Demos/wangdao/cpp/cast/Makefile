.PHONY: all clean
all: cast-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

cast-test: cast.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: cast-test
	./cast-test
