function DepositForm(props) {
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
        <div className="form">
            <h3 className="header">Deposit</h3>
			<label className="formLabel" for="amount">Amount:</label>
            <input className="adjInput" autoFocus spellcheck="false" autocomplete="off" type="text" name="amount" onChange={onChange} value={props.state.amount} /><span className="blink"></span>
        </div>
    );
}