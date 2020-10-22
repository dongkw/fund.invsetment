package fund.investment.userinterface.approval;

import java.time.LocalDateTime;
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

import fund.investment.domain.queryhandler.approval.command.ApprovalAllEventQuerier;
import fund.investment.domain.queryhandler.approval.command.ApprovalEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.approval.ApprovalEventEntry;
import fund.investment.util.SwaggerTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/dataset/approvals")
public class ApprovalController {
	
	@Autowired
    private QueryGateway queryGateway;
	
	@RequestMapping(value = "/{aprvId}", method = RequestMethod.GET)
	@ApiOperation(value = "findById", tags = SwaggerTag.APPROVAL)
	public ResponseEntity<ApprovalEventEntry> findById(@PathVariable("aprvId") String id) {
		try {
			ApprovalEventEntry result = queryGateway.query(ApprovalEventByIdQuerier.builder().id(id).build(), ResponseTypes.instanceOf(ApprovalEventEntry.class)).join();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "findAll", tags = SwaggerTag.APPROVAL)
	public ResponseEntity<List<ApprovalEventEntry>> findAll() {
		try {
			List<ApprovalEventEntry> result = queryGateway.subscriptionQuery(ApprovalAllEventQuerier.builder().time(LocalDateTime.now()).build(), ResponseTypes.multipleInstancesOf(ApprovalEventEntry.class), ResponseTypes.instanceOf(ApprovalEventEntry.class)).initialResult().block();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
