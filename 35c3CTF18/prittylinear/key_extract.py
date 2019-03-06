from collections import defaultdict
import pyshark
import string
cap = pyshark.FileCapture('survillance.pcap')

streams = defaultdict(list)

for p in cap:
    t = p['tcp']
    p = t.get('payload')
    if p:
        data = "".join([chr(int(c, 16))
                        for c in t.get('payload').split(":")])
        streams[t.stream].append(data)

print("R = IntegerModRing(340282366920938463463374607431768211297)")

challenges = []
responses = []
for k, v in streams.items():
    challenge, response, out = v
    print(out)
    challenges.append(list(map(int, challenge.split())))
    responses.append(int(response))
print("M = Matrix(R, [")
first = True
for c in challenges:
    if not first:
        print(",")
    first = False
    print(c, end="")
print("])")
print("b = vector(R,[")
first = True
for r in responses:
    if not first:
        print(",", end="")
    first = False
    print(r)
print("])")
print("print(M.solve_right(b))")