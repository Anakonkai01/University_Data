import matplotlib.pyplot as plt

# 6A
print("6A")
def pmf_bernoulli(p, x):
    return (p**x) * (1 - p)**(1 - x);

def plot_pmf_bernoulli(pmf):
    X = [0,1]
    P_bernoulli = [pmf_bernoulli(pmf,x) for x in X]
    plt.plot(X,P_bernoulli,'o')
    plt.title('PMF of bernoulli (p=%.2f)'%(pmf))
    plt.xlabel('Value')
    plt.ylabel('Probability')
    plt.show()

plot_pmf_bernoulli(0.5)

import math
def pmf_binomial(n,k,p):
    return math.comb(n,k) * p**k * (1-p)**(n-k)

def plot_pmf_binom(n,p):
    K = list(range(0,n+1))
    P_binom = [pmf_binomial(n, k, p) for k in K]
    plt.plot(K,P_binom,'-o')
    axes = plt.gca()
    axes.set_xlim([0,n])
    axes.set_ylim([0,1])

    plt.ylabel('Probability')
    plt.show()

plot_pmf_binom(10, 0.5)

def pmf_poisson(k,lam):
    return math.exp(-lam) * lam**k / math.factorial(k)


def plot_pmf_poisson(n,lam):
    K = list(range(0,n+1))
    P_poisson = [pmf_poisson(k, lam) for k in K]
    plt.plot(K,P_poisson,'-o')
    plt.title('PMF of poisson (lambda=%.2f)'%(lam))
    plt.xlabel('Value')
    plt.ylabel('Probability')
    plt.show()

plot_pmf_poisson(25,5)

def pmf_geo(p,x):
    return (1 - p)**(x-1) * p

def plot_pmf_geo(p,n):
    X = list(range(1,n+1))
    P_geo = [pmf_geo(p,x) for x in X]
    plt.plot(X,P_geo,'-o')
    plt.title('PMF of geometric (p=%.2f)'%(p))
    plt.xlabel('Value')
    plt.ylabel('Probability')
    plt.show()


plot_pmf_geo(0.5,10)


def exercise1():
    # a
    print("a:")
    print(pmf_binomial(5,2,0.1))
    
    # b
    print("b:")
    plot_pmf_binom(5,0.1)
print("exercise1a:")
exercise1()


def exercise2():
    # a
    print("a:")
    print(pmf_poisson(2,3))
    
    # b
    print("b:")
    plot_pmf_poisson(5,3)

print("exercise 2a  ")
exercise2()

def exercise3():
    #a 
    print("a")
    print(pmf_geo(0.4,3))

    #b
    print("b:")
    plot_pmf_geo(0.4,10)
print("exercise 3a")
exercise3()

# 6B
print("6B:")
def generate_data(a,b,size):
    n = (b-a)/(size-1)
    res = []
    s = a
    while s < b:
        res.append(s)
        s += n
    if len(res) < size:
        res.append(b)
    return res


def pmf_uniform_continous(a,b):
    return 1/(b-a)

def plot_pmf_uniform_continous(a,b):
    X = generate_data(a,b,1000)
    P_uniform = [pmf_uniform_continous(a,b) for x in X]
    plt.plot(X,P_uniform,'-')
    plt.plot([a,a],[0,1/(b-a)],'g--')
    plt.plot([b,b],[0,1/(b-a)],'g--')

    plt.title('PMF of uniform continuous (a=%.2f, b=%.2f)'%(a,b))
    plt.xlabel('Value')
    plt.ylabel('Probability')
    plt.show()

plot_pmf_uniform_continous(4,6)

def pmf_normal(x,mu,sigma):
    return (1/(sigma*math.sqrt(2*math.pi))) * math.exp(-0.5 * ((x - mu)/sigma)**2)

def cdf_normal(x,mu,sigma):
    return 0.5 * (1 + math.erf((x - mu) / (sigma * math.sqrt(2))))

def plot_pmf_normal(mu,sigma):
    X = generate_data(mu-4*sigma,mu + 4*sigma,1000)
    P_normal = [pmf_normal(x,mu,sigma) for x in X]
    plt.plot(X,P_normal,'-')

    plt.title('PMF of normal (μ=%.2f, σ=%.2f)'%(mu,sigma))
    plt.xlabel('Value')
    plt.ylabel('Probability')
    plt.show()

plot_pmf_normal(0,1.5)


def plot_cdf_normal(mu,sigma):
    X = generate_data(mu-4*sigma,mu + 4*sigma,1000)
    P_normal = [cdf_normal(x,mu,sigma) for x in X]
    plt.plot(X,P_normal,'-')

    plt.title('CDF of normal (μ=%.2f, σ=%.2f)'%(mu,sigma))
    plt.xlabel('Value')
    plt.ylabel('Probability')
    plt.show()

print("exercise 1b")
plot_cdf_normal(0,1.5)

def exercise2b():
    Fa = cdf_normal(9,10,1)
    Fb = cdf_normal(12,10,1)
    P_a_b = Fb - Fa
    print(P_a_b)





print("exercise 2b")
exercise2b()

