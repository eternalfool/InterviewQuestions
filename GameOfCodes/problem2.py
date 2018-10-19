from common import *
import random
from messages import messages
from collections import defaultdict


def print_results(cnt):
    """
    print ally count of a ruler
    :param cnt: ally counter
    :return:
    """
    for elem, count in cnt.most_common():
        print("Allies for {} : {}".format(elem, count))


def calculate_winner(universe):
    """
    calculate the winner of the ballot in the universe.
    If there are multiple winners return both. If there are no winners return none.
    :param universe:
    :return: winners and dict of winners to allies
    """
    diplomacy = defaultdict(list)
    for kingdom in universe.kingdom_list:
        if kingdom.ruler:
            diplomacy[kingdom.ruler].append(kingdom.name)
    cnt = Counter([x.ruler for x in universe.kingdom_list if x.ruler])
    print_results(cnt)
    results = cnt.most_common()
    if len(results) == 0:
        return None, None
    max_votes = results[0][1]
    winners = [x[0] for x in results if cnt[x[0]] == max_votes]
    return winners, diplomacy


def generate_messages(competitors, non_competitors):
    """
    for every competitor send a random message to a non-competitor
    :param competitors:
    :param non_competitors:
    :return:
    """
    message_list = []
    for competitor in competitors:
        for non_competitor in non_competitors:
            message_list.append(Message(competitor, non_competitor, random.choice(messages)))
    return message_list


def start_ballot(southeros, competitors, non_competitors, ballot_count):
    """
    Conduct ballot to select most popular ruler. Ballot is conducted again in case of tie.
    :param southeros:
    :param competitors:
    :param non_competitors:
    :param ballot_count:
    :return:
    """
    southeros.invalidate_rulers()
    print("Starting ballot count: {}".format(ballot_count))
    message_list = generate_messages(competitors, non_competitors)
    for message in message_list:
        southeros.send_message(message)
    winners, diplomacy = calculate_winner(southeros)
    # If nobody got a vote
    if not winners:
        start_ballot(southeros, competitors, non_competitors, ballot_count + 1)
    # If tie
    elif len(winners) > 1:
        start_ballot(southeros, winners, non_competitors, ballot_count + 1)
    else:
        southeros.ruler = winners[0]
        southeros.ruler_allies = diplomacy[winners[0]]


if __name__ == "__main__":
    southeros = Southeros()
    while True:
        ip = input()
        if ip == "Who is the ruler of Southeros?":
            print(southeros.ruler)
        elif ip == "Allies of Ruler?":
            allies = southeros.ruler_allies
            if not allies or len(allies) == 0:
                print(None)
            else:
                print(", ".join(allies))
            # print("Enter the kingdoms competing to be the ruler:")
        else:
            competitors = ip.split()
            non_competitors = list(set(southeros.name_list) - set(competitors))
            start_ballot(southeros, competitors, non_competitors, ballot_count=1)


# Sample input
# Who is the ruler of Southeros?
# Allies of Ruler?
# Ice Space Air
# Who is the ruler of Southeros?
# Allies of Ruler?

# Sample output
# None
# None
# Starting ballot count: 1
# Starting ballot count: 2
# Allies for Space : 2
# Space
# Land, Fire
