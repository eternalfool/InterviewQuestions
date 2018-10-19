import unittest

from common import Southeros, king_shan, Message
import random


# Tests for problem1

class Unittests(unittest.TestCase):
    def setUp(self):
        self.southeros = Southeros()

    def test_ruler_problem_1(self):
        kingdoms = self.southeros.kingdom_list
        random.shuffle(kingdoms)
        random_kingdoms = kingdoms[:3]
        for kingdom in random_kingdoms:
            kingdom.ruler = king_shan
        assert self.southeros.get_ruler_problem_1() == king_shan

    def test_ruler_sending_messages(self):
        messages = [Message(king_shan, "Air", "oaaawaala"), Message(king_shan, "Land", "a1d22n333a4444p"),
                    Message(king_shan, "Ice", "zmzmzmzaztzozh")]
        for message in messages:
            self.southeros.send_message(message)
        assert self.southeros.get_ruler_problem_1() == king_shan

    def test_message(self):
        message = Message(king_shan, "Ice", "Ahoy! Fight for me with men and money")
        self.southeros.send_message(message)
        assert self.southeros.name_to_kingdom['Ice'].ruler == king_shan
