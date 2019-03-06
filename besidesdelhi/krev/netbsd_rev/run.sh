#!/bin/bash

qemu-system-i386 -m 1024 -hda ./netbsd.img -redir tcp:5022::22 $1 $2
