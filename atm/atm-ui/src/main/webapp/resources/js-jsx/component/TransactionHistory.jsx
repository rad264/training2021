function TransactionHistory(props) {
	
	let history = props.state.transactions.reverse().slice(props.state.transactionIndex, props.state.transactionIndex + 10);
	let table =  (history.map(h => {
						return (
							<tr>
								<td>{h.transactionId}</td>
								<td>{h.createdTime.slice(0, 10)}</td>
								<td>{h.createdTime.slice(10)}</td>
								<td>{Number(h.amount).toFixed(2)}</td>
								<td>{h.type}</td>
								<td>{h.description}</td>
							</tr>
						)
					}))
	
	React.useEffect(() => {
		console.log(props.state.transactionIndex)
		history = props.state.transactions.reverse().slice(props.state.transactionIndex, props.state.transactionIndex + 10);
		table =  (history.map(h => {
						return (
							<tr>
								<td>{h.transactionId}</td>
								<td>{h.createdTime.slice(0, 10)}</td>
								<td>{h.createdTime.slice(10)}</td>
								<td>{Number(h.amount).toFixed(2)}</td>
								<td>{h.type}</td>
								<td>{h.description}</td>
							</tr>
						)
					}))
	}, [props.state])

    return (
        <div>
            <h3 className="header">Transaction History</h3>
			<table className="historyTable">
				<tr>
					<th>ID</th>
					<th>Date</th>
					<th>Time</th>
					<th>Amount</th>
					<th>Type</th>
					<th>Detail</th>
				</tr>
		            {table}
			</table>
        </div>
    );
}