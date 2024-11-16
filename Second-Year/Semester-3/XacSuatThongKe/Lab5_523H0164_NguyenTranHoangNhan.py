

import random
import math
import scipy





def exercise1():
    rolls = [random.randint(1, 6) for _ in range(10000)]
    counts = {}

    for i in range(1, 7):
        count = 0
        for roll in rolls:
            if roll == i:
                count += 1
        counts[i] = count

    total_rolls = len(rolls)

    pmf = {}

    for outcome, count in counts.items():
        probability = count / total_rolls
        pmf[outcome] = probability

    print("PMF")
    print(pmf)

def exercise2():
    normal_dist = scipy.stats.norm(loc=0, scale=1)

    x_values = []
    for i in range(-40, 41):
        x = i / 10
        x_values.append(x)

    pdf_values = []
    for x in x_values:
        pdf = normal_dist.pdf(x)
        pdf_values.append(pdf)

    print("x     |     pdf")
    print("---------------------------------")
    for i in range(0, len(x_values), 8): # Print every 8th value
        x = x_values[i]
        pdf = pdf_values[i]
        print(f"{x:8.2f} | {pdf:.6f}")



def exercise3():
    def uniform_cdf(x, a=0, b=1):
        return (x - a) / (b - a)

    x_values = [x / 10.0 for x in range(0, 11)]             

    for x in x_values:
        print(f"x: {x}, cdf: {uniform_cdf(x)}")

def exercise4():
    pmf = {1: 0.2, 2: 0.5, 3: 0.3}
    expectation = sum([x * p for x, p in pmf.items()])
    
    print("Expectation E(X):", expectation)



def exercise5():
    pmf = {1: 0.2, 2: 0.5, 3: 0.3}
    expectation = sum([x * p for x, p in pmf    .items()])
    expectation_squared = sum([x**2 * p for x, p in pmf.items()])
    variance = expectation_squared - expectation**2
    
    print("Variance Var(X):", variance)


def exercise6():
    x = [0, 5, 2, 3, 4, 0, 2, 3, 5, 1] 
    values = list(set(x))  
    pmf = {value: x.count(value) / len(x) for value in values}

    expectation = sum([value * prob for value, prob in pmf.items()])
    expectation_squared = sum([value**2 * prob for value, prob in pmf.items()])
    variance = expectation_squared - expectation**2
    standard_deviation = math.sqrt(variance)
    
    prob_3_or_more = sum([pmf[value] for value in values if value >= 3])
    
    print("PMF:", pmf)
    print("Expectation:", expectation)
    print("Variance:", variance)
    print("Standard Deviation:", standard_deviation)
    print("Probability of 3 or more cases:", prob_3_or_more)



def exercise7():
    flips = [random.randint(0, 2) for _ in range(10000)]  
    values = list(set(flips)) 
    pmf = {value: flips.count(value) / len(flips) for value in values}

    expectation = sum([value * prob for value, prob in pmf.items()])
    expectation_squared = sum([value**2 * prob for value, prob in pmf.items()])
    variance = expectation_squared - expectation**2
    std_dev = math.sqrt(variance)
    
    prob_at_least_one_head = sum([pmf[value] for value in values if value >= 1])
    
    print("PMF:", pmf)
    print("Expectation:", expectation)
    print("Variance:", variance)
    print("Standard Deviation:", std_dev)
    print("Probability of at least one head:", prob_at_least_one_head)

print("exercise1: ")
exercise1()
print()
print("exercise2: ")
exercise2()
print()
print("exercise3: ")
exercise3()
print()
print("exercise4: ")
exercise4()
print()
print("exercise5: ")
exercise5()
print()
print("exercise6: ")
exercise6()
print()
print("exercise7: ")
exercise7()
