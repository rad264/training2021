function WithdrawForm(props) {
    const onChange = (event) => {
		let amount = event.target.value
		props.state = {
			...props.state,
			amount: amount,
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
				<div className="form">
		            <h3 className="header">Enter an amount to withdraw</h3>
					<label className="formLabel" for="amount">Amount:</label>
		            <input className="adjInput" autoFocus spellcheck="false" autocomplete="off" type="text" name="amount" onChange={onChange} value={props.state.amount} /><span className="blink"></span>
		        </div>
				: <>
					<h3 className="header">{props.state.message}<span className="blink"></span></h3>
					{props.state.amount &&
						<div className="subHeader">Deposit amount: ${Number(props.state.amount).toFixed(2)}</div>}
				</>}
		</>
    );
}