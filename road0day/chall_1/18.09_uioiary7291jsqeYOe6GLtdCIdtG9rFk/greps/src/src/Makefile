all: grep

LIBOBJ = \
	dfasearch-pre.o \
	grep-pre.o \
	kwsearch-pre.o \
	kwset-pre.o \
	pcresearch-pre.o \
	searchutils-pre.o

.SECONDARY:
%-pre.c :
	gcc -std=gnu11 -include stdio.h -DHAVE_CONFIG_H -I. -I..  -I../lib -I../lib -g -O2 -E -o  $@ $(shell echo "$@" | sed -e "s/-pre//")
	sed -i '/^#/ d' $@

%.o : %.c
	$(CC) $(CFLAGS) -std=gnu11 -DHAVE_CONFIG_H -g -I. -I.. -I../lib -I../lib -O2 -c -o $@ $< 

grep : $(LIBOBJ)
	$(CC) $(CFLAGS) -std=gnu11 -O2 -o grep -g -I. -I.. -I../lib $(LIBOBJ) ../lib/libgreputils.a  ../lib/libgreputils.a

preclean : 
	rm -f *-pre.c
	rm -f *-pre.h

clean : 
	rm -f *.o \
	rm -f *.so \
	rm -f grep \
	rm -f *.Tpo 
