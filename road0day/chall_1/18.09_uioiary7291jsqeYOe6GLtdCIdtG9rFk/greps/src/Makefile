all: grep

grep :
	make -C lib
	make -C src

clean:
	rm -rf lava-install
	make -C lib clean
	make -C src clean

install:
	rm -rf lava-install
	mkdir -p lava-install/bin
	cp src/grep lava-install/bin
