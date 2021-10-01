function TransferForm(props) {
    const onChangeAmount = (event) => {
		let amount = event.target.value
		props.state = {
			...props.state,
			amount: amount,
		}
		props.updateState(props.state)
	}
	
	const onChangeAccount = (event) => {
		if (isNaN(event.target.value)){
			props.state = {
				...props.state,
				alert: "Invalid Input: Digits only"
			}
		} else {
			console.log(event.target.value.length)
			if (event.target.value.length > 6){
				props.state = {
					...props.state,
					alert: "Account number must be 6-digits"
				}
			} else {
				let accountNumber = event.target.value
				props.state = {
					...props.state,
					amount: accountNumber
				}
			}
		}
		props.updateState(props.state)
	}
	
	
	function resizeInput() {
		
	  	this.style.width = 0; this.style.width = (this.scrollWidth)+ 'px';
	}
	
	React.useEffect(() => {
		let input = document.querySelector('input');
		input.addEventListener('input', resizeInput);
		resizeInput.call(input); 
	}, [props.state])
	
    return (
		<>
			{props.state.keyPad ?
				props.state.message.includes("account") ?
					<div className="form">
			            <h3 className="header">{props.state.message}</h3>
						<label className="formLabel" for="amount">Account Number</label>
			            <input className="adjInput" autoFocus spellcheck="false" autocomplete="off" type="text" name="amount" onChange={onChangeAccount} value={props.state.amount} /><span className="blink"></span>
			        </div>
					: <div className="form">
			            <h3 className="header">{props.state.message}</h3>
						<label className="formLabel" for="amount">Amount</label>
			            <input className="adjInput" autoFocus spellcheck="false" autocomplete="off" type="text" name="amount" onChange={onChangeAmount} value={props.state.amount} /><span className="blink"></span>
			        </div>
				: <>
					<h3 className="header">{props.state.message}<span className="blink"></span></h3>
					{props.state.amount &&
						<>
							<div className="subHeader">Amount: ${Number(props.state.amount).toFixed(2)}</div>
							<div className="subHeader">From: Account # {props.state.transfer.from}</div>
							<div className="subHeader">To: Account # {props.state.transfer.to}</div>
						</>}
				</>}
		</>
    );
}