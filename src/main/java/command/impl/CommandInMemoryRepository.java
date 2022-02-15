package command.impl;

import command.api.CommandRepository;
import message.impl.AbstractInMemoryMessageRepository;

public class CommandInMemoryRepository extends AbstractInMemoryMessageRepository<BasicCommandMessage> implements CommandRepository {
}
