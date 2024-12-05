from itertools import chain, combinations

def powerset(s):
    """Generate all subsets of a set."""
    return chain.from_iterable(combinations(s, r) for r in range(len(s)+1))

def closure(attributes, fds):
    """Compute the closure of a set of attributes given the functional dependencies."""
    result = set(attributes)
    while True:
        new_result = set(result)
        for lhs, rhs in fds:
            if set(lhs).issubset(result):
                new_result.update(rhs)
        if new_result == result:
            break
        result = new_result
    return result

def find_keys(attributes, fds):
    """Find all keys for a given set of attributes and functional dependencies."""
    keys = []
    for subset in powerset(attributes):
        closure_subset = closure(subset, fds)
        if closure_subset == attributes:
            if not any(set(key).issubset(subset) for key in keys):
                keys.append(subset)
    return keys

# Example usage
attributes = {'A', 'B', 'C', 'D', 'E', 'G', 'H'}
fds = [
    (['A'], ['B', 'C']),
    (['B', 'E'], ['G']),
    (['E'], ['D']),
    (['D'], ['G']),
    (['A'], ['B']),
    (['A', 'G'], ['B', 'C']),
]

keys = find_keys(attributes, fds)
print("Candidate Keys:", keys)
