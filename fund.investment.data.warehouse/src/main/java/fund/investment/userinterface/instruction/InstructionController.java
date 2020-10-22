package fund.investment.userinterface.instruction;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fund.investment.domain.queryhandler.instruction.command.InstructionAllEventQuerier;
import fund.investment.domain.queryhandler.instruction.command.InstructionEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.instruction.InstructionEventEntry;
import fund.investment.util.SwaggerTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/investment/instructions")
public class InstructionController {
	
	@Autowired
    private QueryGateway queryGateway;
	
	@RequestMapping(value = "/{istrId}", method = RequestMethod.GET)
	@ApiOperation(value = "findById", tags = SwaggerTag.INSTRUCTION)
	public ResponseEntity<InstructionEventEntry> findById(@PathVariable("istrId") String istrId) {
		try {
			InstructionEventEntry result = queryGateway.query(InstructionEventByIdQuerier.builder().id(istrId).build(), ResponseTypes.instanceOf(InstructionEventEntry.class)).join();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "findAll", tags = SwaggerTag.INSTRUCTION)
	public ResponseEntity<List<InstructionEventEntry>> findAll() {
		try {
			List<InstructionEventEntry> result = queryGateway.subscriptionQuery(
					InstructionAllEventQuerier.builder().build(), 
					ResponseTypes.multipleInstancesOf(InstructionEventEntry.class),
					ResponseTypes.instanceOf(InstructionEventEntry.class)).initialResult().block();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
