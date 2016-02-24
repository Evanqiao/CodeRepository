.PHONY: all clean
all: new-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

new-test: new.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: new-test
	./new-test
