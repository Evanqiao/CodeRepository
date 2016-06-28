.PHONY: all clean
all: sizeof-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

sizeof-test: sizeof.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: sizeof-test
	./sizeof-test
