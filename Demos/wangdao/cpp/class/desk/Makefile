.PHONY: all clean
all: desk-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

desk-test: desk.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: desk-test
	./desk-test
