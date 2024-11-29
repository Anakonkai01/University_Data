import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from scipy.stats import norm


def exercise1():
    n_simulations = 10000

    # a
    dice_rolls = np.random.randint(1, 7, size=(n_simulations, 2))
    x = dice_rolls.sum(axis=1)

    # b
    random_variable_X = np.unique(x)

    # c
    P = [(x == val).mean() for val in random_variable_X]

    # d
    expectation = np.mean(x)
    variance = np.var(x)
    std_deviation = np.std(x)

    print("Problem 1 Results:")
    print(f"Expectation: {expectation}")
    print(f"Variance: {variance}")
    print(f"Standard Deviation: {std_deviation}")
    print("Probability Distribution Function (PDF):")
    for val, prob in zip(random_variable_X, P):
        print(f"P(X = {val}) = {prob:.4f}")


def exercise2():
    x_values = np.linspace(-10, 10, 500)
    mu = 3
    sigma = 4  

    # a
    pmf_normal = norm.pdf(x_values, loc=mu, scale=sigma)

    # b
    cdf_normal = norm.cdf(x_values, loc=mu, scale=sigma)

    # c
    p_x_between_2_and_7 = norm.cdf(7, loc=mu, scale=sigma) - norm.cdf(2, loc=mu, scale=sigma)

    print("\nProblem 2 Results:")
    print(f"P(2 < X < 7): {p_x_between_2_and_7:.4f}")

    # Plot PMF
    plt.figure()
    plt.plot(x_values, pmf_normal, label="PMF of Normal Distribution")
    plt.title("PMF of Normal Distribution")
    plt.xlabel("X")
    plt.ylabel("PMF")
    plt.legend()
    plt.show()

    # Plot CDF
    plt.figure()
    plt.plot(x_values, cdf_normal, label="CDF of Normal Distribution")
    plt.title("CDF of Normal Distribution")
    plt.xlabel("X")
    plt.ylabel("CDF")
    plt.legend()
    plt.show()


def exercise3():
    sales_data = pd.read_csv('company-sales_data.csv')
    months = sales_data['month_number']
    toothpaste_sales = sales_data['toothpaste']
    shampoo_sales = sales_data['shampoo']
    facecream_sales = sales_data['facecream']

    plt.figure()
    plt.plot(months, toothpaste_sales, label="Toothpaste Sales")
    plt.plot(months, shampoo_sales, label="Shampoo Sales")
    plt.plot(months, facecream_sales, label="Facecream Sales")
    plt.title("Monthly Sales Data")
    plt.xlabel("Month")
    plt.ylabel("Sales")
    plt.legend()
    plt.show()


def exercise4():
    sample_text = """lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur excepteur sint occaecat cupidatat non proident sunt in culpa qui officia deserunt mollit anim id est laborum """
    word_list = sample_text.split()
    word_frequencies = {word: word_list.count(word) for word in set(word_list)}

    # Plot histogram
    plt.figure()
    plt.hist(word_frequencies.values(), bins=30)
    plt.title("Word Frequency Histogram")
    plt.xlabel("Frequency")
    plt.ylabel("Number of Words")
    plt.show()

    print("\nProblem 4 Results:")
    print("Word Frequencies:")
    for word, freq in word_frequencies.items():
        print(f"'{word}': {freq}")


print("Exercise 1:")
exercise1()
print("\nExercise 2:")
exercise2()
print("\nExercise 3:")
exercise3()
print("\nExercise 4:")
exercise4()
