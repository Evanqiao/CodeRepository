.PHONY: all clean
all: student-test

SHELL=/bin/bash
CFLAGS=-O3 -Wall

student-test: student.o
	g++ $^ ${CFLAGS} -o $@


clean:
	rm -f *.o *-test

test: student-test
	./student-test
