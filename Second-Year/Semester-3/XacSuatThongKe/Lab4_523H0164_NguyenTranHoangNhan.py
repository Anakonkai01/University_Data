import itertools
import random


def exercise1():
    def roll_dice():
        return random.randint(1, 6), random.randint(1, 6)

    def experimental_probability(n):
        both_same = 0
        both_different = 0
        both_even = 0
        both_odd = 0
        one_even_one_odd = 0
        both_six = 0
        sum_greater_than_10 = 0

        for _ in range(n):
            die1, die2 = roll_dice()

            if die1 == die2:
                both_same += 1
            else:
                both_different += 1

            if die1 % 2 == 0 and die2 % 2 == 0:
                both_even += 1

            if die1 % 2 == 1 and die2 % 2 == 1:
                both_odd += 1

            if (die1 % 2 == 0 and die2 % 2 == 1) or (die1 % 2 == 1 and die2 % 2 == 0):
                one_even_one_odd += 1

            if die1 == 6 and die2 == 6:
                both_six += 1

            if die1 + die2 > 10:
                sum_greater_than_10 += 1

        probabilities = {
            "both_same": both_same / n,
            "both_different": both_different / n,
            "both_even": both_even / n,
            "both_odd": both_odd / n,
            "one_even_one_odd": one_even_one_odd / n,
            "both_six": both_six / n,
            "sum_greater_than_10": sum_greater_than_10 / n,
        }

        return probabilities

    n = 100000
    probabilities = experimental_probability(n)

    for event, probability in probabilities.items():
        print(f"Probability of {event}: {probability:.4f}")


def exercise2():
    def cross(A, B):
        return {a + b for a in A for b in B}

    urn = cross('W', '12345678') | cross('B', '123456') | cross('R', '123456789')

    urn = list(urn)

    def combos(items, n):
        return {' '.join(combo) for combo in itertools.combinations(items, n)}

    def pick_3_balls():
        return random.sample(urn, 3)

    def experimental_probability(n):
        all_same_color = 0
        all_different_colors = 0
        two_same_color = 0
        two_red_one_white = 0
        all_white_cases = 0

        for _ in range(n):
            balls = pick_3_balls()

            white_count = len([ball for ball in balls if ball.startswith('W')])
            blue_count = len([ball for ball in balls if ball.startswith('B')])
            red_count = len([ball for ball in balls if ball.startswith('R')])

            if white_count == 3 or blue_count == 3 or red_count == 3:
                all_same_color += 1

            if white_count == 1 and blue_count == 1 and red_count == 1:
                all_different_colors += 1

            if (white_count == 2 and (blue_count == 1 or red_count == 1)) or \
                    (blue_count == 2 and (white_count == 1 or red_count == 1)) or \
                    (red_count == 2 and (white_count == 1 or blue_count == 1)):
                two_same_color += 1

            if red_count == 2 and white_count == 1:
                two_red_one_white += 1

            if white_count == 3:
                all_white_cases += 1

        probabilities = {
            "all_same_color": all_same_color / n,
            "all_different_colors": all_different_colors / n,
            "two_same_color": two_same_color / n,
            "two_red_one_white": two_red_one_white / n,
            "all_white_cases": all_white_cases,  # Liệt kê số trường hợp
        }

        return probabilities

    n = 10000
    probabilities = experimental_probability(n)

    for event, probability in probabilities.items():
        if event == "all_white_cases":
            print(f"Number of cases where all 3 balls are white: {probability}")
        else:
            print(f"Probability of {event}: {probability:.4f}")

def exercise3():
    suits = ['Heart', 'Diamond', 'Club', 'Spade']
    values = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']

    deck = [f"{value} of {suit}" for value in values for suit in suits]

    def pick_4_cards():
        return random.sample(deck, 4)

    def experimental_probability(n):
        same_suit = 0
        different_suits = 0
        same_color = 0
        same_value = 0
        all_numbers = 0
        all_faces = 0

        for _ in range(n):
            cards = pick_4_cards()

            card_suits = [card.split(' of ')[1] for card in cards]
            card_values = [card.split(' of ')[0] for card in cards]

            red_suits = {'Hearts', 'Diamonds'}
            black_suits = {'Clubs', 'Spades'}
            card_colors = ['Red' if suit in red_suits else 'Black' for suit in card_suits]

            face_cards = {'J', 'Q', 'K', 'A'}
            number_cards = [value for value in card_values if value not in face_cards]
            face_card_count = len([value for value in card_values if value in face_cards])

            if len(set(card_suits)) == 1:
                same_suit += 1

            if len(set(card_suits)) == 4:
                different_suits += 1

            if len(set(card_colors)) == 1:
                same_color += 1

            if len(set(card_values)) == 1:
                same_value += 1

            if len(number_cards) == 4:
                all_numbers += 1

            if face_card_count == 4:
                all_faces += 1

        probabilities = {
            "same_suit": same_suit / n,
            "different_suits": different_suits / n,
            "same_color": same_color / n,
            "same_value": same_value / n,
            "all_numbers": all_numbers / n,
            "all_faces": all_faces / n,
        }

        return probabilities

    n = 100000
    probabilities = experimental_probability(n)

    for event, probability in probabilities.items():
        print(f"Probability of {event}: {probability:.4f}")

def exercise4():
    def cross(A, B):
        return {a + b for a in A for b in B}
    def combos(items, n):
        return {' '.join(combo) for combo in itertools.combinations(items, n)}

    URN = cross('W', '12') | cross('B', '123') | cross('R', '1234')
    print("URN:", URN)
    U3 = combos(URN, 3)
    print("U3:", U3)

    white_balls = {'W1', 'W2'}
    blue_balls = {'B1', 'B2', 'B3'}
    red_balls = {'R1', 'R2', 'R3', 'R4'}

    white1blue1red1 = {f'{w} {b} {r}' for w in white_balls for b in blue_balls for r in red_balls}
    print("white1blue1red1:", white1blue1red1)

    total_combination = len(U3)

    outcomes = len(white1blue1red1)

    P = outcomes / total_combination
    print("P:", P)


def exercise5():
    def is_straight(hand):
        values = [card % 13 for card in hand]
        values.sort()
        if values == [0, 1, 2, 3, 12]:
            return True
        return all(values[i] == values[i - 1] + 1 for i in range(1, 5))

    def simulate_hands(num_simulations):
        straight_count = 0
        for _ in range(num_simulations):
            deck = list(range(52))
            hand = random.sample(deck, 5)
            if is_straight(hand):
                straight_count += 1
        return straight_count / num_simulations

    num_simulations = 1000000
    empirical_probability = simulate_hands(num_simulations)

    print(f"Practical probability: {empirical_probability:.6f}")
    print(f"Theorical probability: {10240 / 2598960:.6f}")


def exercise6():
    E = {0, 1, 2, 3, 4, 5}

    three_digit_numbers = []

    for hundreds in E - {0}:
        for tens in E:
            for ones in E:
                number = hundreds * 100 + tens * 10 + ones
                three_digit_numbers.append(number)

    print(f"The number of 3-digits numbers in which every digit is an element in E: {len(three_digit_numbers)}")
    print("All of 3-digits numbers in which every digit is an element in E:")
    print(three_digit_numbers)


    E = {0, 1, 2, 3, 4, 5}

    four_digit_numbers = []

    for perm in itertools.permutations(E, 4):
        if perm[0] != 0:
            number = perm[0] * 1000 + perm[1] * 100 + perm[2] * 10 + perm[3]
            four_digit_numbers.append(number)

    print(f"The number of 4-digits numbers in which all digits are pairwise different and every digit is an element in E: {len(four_digit_numbers)}")
    print("All 4-digits numbers in which all digits are pairwise different and every digit is an element in E:")
    print(four_digit_numbers)


def exercise7():
    def disease_diagnosis(prevalence, sensitivity, false_positive_rate):
        p_d = prevalence
        p_not_d = 1 - p_d
        p_pos_given_d = sensitivity
        p_pos_given_not_d = false_positive_rate

        p_d_given_pos = (p_pos_given_d * p_d) / (p_pos_given_d * p_d + p_pos_given_not_d * p_not_d)
        return p_d_given_pos

    def weather_prediction(p_rain, accuracy_when_rain, false_rain_prediction):
        p_not_rain = 1 - p_rain
        p_forecast_given_rain = accuracy_when_rain
        p_forecast_given_not_rain = false_rain_prediction

        p_rain_given_forecast = (p_forecast_given_rain * p_rain) / (
                    p_forecast_given_rain * p_rain + p_forecast_given_not_rain * p_not_rain)
        return p_rain_given_forecast

    def disease_testing(prevalence, sensitivity, specificity):
        p_d = prevalence
        p_not_d = 1 - p_d
        p_neg_given_not_d = specificity
        p_neg_given_d = 1 - sensitivity

        p_not_d_given_neg = (p_neg_given_not_d * p_not_d) / (p_neg_given_not_d * p_not_d + p_neg_given_d * p_d)
        return p_not_d_given_neg

    # a
    result_a = disease_diagnosis(prevalence=0.01, sensitivity=0.98, false_positive_rate=0.05)
    print(f"a. Probability of having the disease given a positive test: {result_a:.4f}")

    # b
    result_b = weather_prediction(p_rain=0.70, accuracy_when_rain=0.80, false_rain_prediction=0.10)
    print(f"b. Probability of rain given a rain forecast: {result_b:.4f}")

    # c
    result_c = disease_testing(prevalence=0.05, sensitivity=0.99, specificity=0.95)
    print(f"c. Probability of not having the disease given a negative test: {result_c:.4f}")



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
