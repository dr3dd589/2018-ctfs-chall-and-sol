# uncompyle6 version 3.2.3
# Python bytecode 2.7 (62211)
# Decompiled from: Python 2.7.15rc1 (default, Nov 12 2018, 14:31:15) 
# [GCC 7.3.0]
# Embedded file name: ./dangerzone.py
# Compiled at: 2018-11-22 12:44:11
import base64

def reverse(s):
    return s[::-1]


def b32decode(s):
    return base64.b32decode(s)


def reversePigLatin(s):
    return s[-1] + s[:-1]


def rot13(s):
    return s.decode('rot13')


def main():
    print 'Something Something Danger Zone'
    a = '=YR2XYRGQJ6KWZENQZXGTQFGZ3XCXZUM33UOEIBJ'
    b = reverse(a)
    c = b32decode(b)
    d = reversePigLatin(c)
    e = rot13(d)
    print(e)

if __name__ == '__main__':
    s = main()
    print s