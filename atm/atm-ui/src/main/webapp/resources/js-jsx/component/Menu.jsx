function Menu(props) {
	
	const [selected, setSelected] = React.useState(props.menu)

	
	React.useEffect(() => {
		setSelected(props.menu)
	}, [props]) 
	
	return (
		<div id="menu-container">
			{selected.map(menu =>  {
				if (!menu.inactive) {
					return <div className="menu" id={menu.id}>{menu.name}</div>
				}
			})}
		</div>
	)
}