package eco.sustainnshare.webapp.mappers;

import eco.sustainnshare.webapp.dto.TransactionDto;
import eco.sustainnshare.webapp.entity.Transactions;
import eco.sustainnshare.webapp.repository.ItemsRepository;
import eco.sustainnshare.webapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionsMapper {
    private final UsersRepository usersRepository;
    private final ItemsRepository itemsRepository;
    public Transactions transactionDtoToEntity(TransactionDto transactionDto){
        return Transactions.builder()
                .item(itemsRepository.getReferenceById(transactionDto.getItemID()))
                .donor(usersRepository.getReferenceById(transactionDto.getDonorID()))
                .receiver(usersRepository.getReferenceById(transactionDto.getReceiverID()))
                .status(transactionDto.getStatus())
                .dateInitiated(transactionDto.getDateInitiated())
                .dateCompleted(transactionDto.getDateCompleted())
                .build();
    }
}