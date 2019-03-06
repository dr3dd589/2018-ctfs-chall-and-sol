#!/usr/bin/env python

a = "U @L^vi>n=i>R9;9<cR9ciR9;9<cR9ciR9;9<cR9ciR9;9<cR9ciRka9;p"
flag = ""
for i in range(len(a)):
    flag += chr(ord(a[i])^13)

print(flag)