export default function Welcome() {
    return (
        <div className="join-container">
            <header className="join-header">
                <h1>Tic-tac-toe</h1>
            </header>
            <main className="join-main">
                <form className="join-form">
                    <label htmlFor="username">Username</label>
                    <input
                        type="text"
                        name="username"
                        id="username"
                        placeholder="Enter username..."
                        required
                    />
                    <button type="submit" className="btn" >Play</button>
                </form>
            </main>
        </div>
    );
}