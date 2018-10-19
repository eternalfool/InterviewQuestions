from collections import Counter

king_shan = "King Shan"


class Southeros:  # Universe

    def __init__(self):
        self.ruler = None
        self.ruler_allies = None
        self.name_to_kingdom = {}
        self.kingdom_list = []
        self.name_list = []
        self.initialize_kingdoms()
        pass

    def get_allies(self, king):
        return [kingdom.name for kingdom in self.kingdom_list if kingdom.ruler == king]

    def send_message(self, message):
        kingdom = self.name_to_kingdom[message.receiver]
        kingdom.receive_message(message)

    def get_ruler_problem_1(self):
        count = len([kingdom.name for kingdom in self.kingdom_list if kingdom.ruler == king_shan])
        return king_shan if count >= 3 else None

    def initialize_kingdoms(self):
        space_kingdom = Kingdom("Space", "gorilla")
        water_kingdom = Kingdom("Water", "octopus")
        land_kingdom = Kingdom("Land", "panda")
        air_kingdom = Kingdom("Air", "owl")
        ice_kingdom = Kingdom("Ice", "mammoth")
        fire_kingdom = Kingdom("Fire", "dragon")
        self.kingdom_list.extend([space_kingdom, water_kingdom, land_kingdom, air_kingdom, ice_kingdom, fire_kingdom])
        for kingdom in self.kingdom_list:
            self.name_to_kingdom[kingdom.name] = kingdom
        self.name_list.extend([k.name for k in self.kingdom_list])

    def invalidate_rulers(self):
        for kingdom in self.kingdom_list:
            kingdom.ruler = None


class Kingdom:
    def __init__(self, name, emblem):
        self.name = name
        self.emblem = emblem
        self.ruler = None

    def __repr__(self):
        return "name: {} emblem: {}".format(self.name, self.emblem)

    def receive_message(self, message):
        if not self.ruler and self.is_convincing_message(message):
            self.ruler = message.sender

    def is_convincing_message(self, message):
        return len(Counter(self.emblem.lower()) - Counter(message.content.lower())) == 0


class Message:

    def __init__(self, sender, receiver, content):
        self.sender = sender
        self.receiver = receiver
        self.content = content

    def __repr__(self):
        return "sender: {} receiver: {} content: {}".format(self.sender, self.receiver, self.content)
