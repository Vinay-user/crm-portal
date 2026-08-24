const bcrypt = require('bcrypt');

async function main() {
    const password = 'Hello@123@';

    const hash = await bcrypt.hash(password, 10);

    console.log('Password:', password);
    console.log('Hash:', hash);
}

main().catch(console.error);