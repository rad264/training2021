function NumberPad(props) {	
	let numbers = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Del", "0", "."]
	
	const onClick = (event) => {
		let newAmount = props.state.amount;
		
		if (event.target.value === "Del") {
			newAmount = newAmount.substring(0, newAmount.length - 1)
		} else {
			newAmount = props.state.amount + event.target.value			
		}
		
		if (props.state.message.includes("account")){
			if (newAmount.length > 6){
				props.state = {
					...props.state,
					amount: newAmount.substring(0,6),
					alert: "Account number must be 6-digits"
				}
			} else {
				props.state = {
					...props.state,
					amount: newAmount,
					alert: ""
				}
			}
		} else {
			props.state = {
				...props.state,
				amount: newAmount,
			}
		}
		props.updateState(props.state)
	}
	
	let padButtons = (
		numbers.map(number => {
			return (
				<>
					{
						<button className="padButtons" disabled={!props.state.keyPad} onClick={onClick} value={number}>{number}</button>
					}
				
				</>
			)
		})
	)
	
	return (
		<div className="numberPad">
			{padButtons}
		</div>
	)
}