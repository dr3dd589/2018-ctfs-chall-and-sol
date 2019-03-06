#!/usr/bin/env python

# import angr


# def basic_symbolic_execution():
#     p = angr.Project('angrme')
#     state = p.factory.entry_state()
#     sm = p.factory.simulation_manager(state)
#     sm.explore()
#     sol = sm.deadended[-1].posix.dumps(0)
#     print(sol,len(sol))

# if __name__ == '__main__':
#     basic_symbolic_execution()


    #hxp{4nd_n0w_f0r_s0m3_r3al_ch4ll3ng3}

import angr
import claripy

proj = angr.Project('angrme')
simgr = proj.factory.simgr()
simgr.explore(find=lambda s: b":)" in s.posix.dumps(1))
s = simgr.found[0]
print(s.posix.dumps(0))