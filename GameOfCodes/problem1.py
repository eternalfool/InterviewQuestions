from common import Southeros, king_shan, Message


# Problem 1 specific function
def is_message(ip):
    return len(ip.split(",")) == 2


# Problem 1 specific function
def decode_message(ip):
    return ip.split(", ")


if __name__ == "__main__":
    southeros = Southeros()
    while True:
        msg = input()
        if msg == "Who is the ruler of Southeros?":
            print(southeros.get_ruler_problem_1())
        elif msg == "Allies of Ruler?" or msg == "Allies of King Shan?":
            allies = southeros.get_allies(king_shan)
            if len(allies) == 0:
                print(None)
            else:
                print(",".join(southeros.get_allies(king_shan)))
        elif is_message(msg):
            kingdom_name, message = decode_message(msg)
            southeros.send_message(Message(king_shan, kingdom_name, message))

# Assumed sample input
# Who is the ruler of Southeros?
# Allies of Ruler?
# Air, oaaawaala
# Land, a1d22n333a4444p
# Ice, zmzmzmzaztzozh
# Who is the ruler of Southeros?
# Allies of Ruler?

# output
# None
# None
# King Shan
# Land,Air,Ice

# Assumed sample input
# Who is the ruler of Southeros?
# Allies of King Shan?
# Air, Let’s swing the sword together
# Land, Die or play the tame of thrones
# Ice, Ahoy! Fight for me with men and money
# Water, Summer is coming
# Fire, Drag on Martin!
# Who is the ruler of Southeros?
# Allies of Ruler?


# output
# None
# None
# King Shan
# Land,Air,Ice,Fire
