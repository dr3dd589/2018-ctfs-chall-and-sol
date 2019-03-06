#!/usr/bin/env python

import angr


def basic_symbolic_execution():
    p = angr.Project('wreckme2')
    state = p.factory.entry_state()
    sm = p.factory.simulation_manager(state)
    sm.explore()
    sol = sm.deadended
    print(sol,len(sol))

if __name__ == '__main__':
    basic_symbolic_execution()
