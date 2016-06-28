.PHONY: all clean
all: singleton-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

singleton-test: singleton.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: singleton-test
	./singleton-test
