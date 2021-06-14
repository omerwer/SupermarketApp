let registreis = [];

const init_registreis = () => {
    for (i = 0; i < 3; i++) {
        registreis.push({
            id: i,
            availableAt: new Date(),
        });
    }
};

const findMinRegistry = () => {
    const start_registry = { id: 999, availableAt: new Date(8640000000000000) }
    console.log(`start_registry - id: ${start_registry.id}, available At:${new Date(start_registry.availableAt).toString()}`);

    return registreis.reduce((acc, cur) => new Date(cur.availableAt) < new Date(acc.availableAt) ? cur : acc, start_registry);
};

init_registreis();

const app = require('express')();
const PORT = 8080;


app.listen(
    PORT,
    () => console.log(`it's alive on http://localhost:${PORT}`)
)

let x = 1;

app.get('/counter', (req, res) => {

    x++;
    console.log(x);

    res.status(200).send({
        counter: x,
    })
})

// get the shortest registry
app.get('/getTurn/:weight', (req, res) => {

    const { weight } = req.params;

    const minReg = findMinRegistry();

    registreis.map(reg => console.log(`id: ${reg.id}, available At:${new Date(reg.availableAt).toString()}`));

    let date = new Date(minReg.availableAt);

    const curTime = new Date();

    if (date < curTime)
        date = curTime;

    const estimate_start = new Date(date.getTime());

    date.setSeconds(date.getSeconds() + parseInt(weight));

    minReg.availableAt = date;

    registreis[minReg.id] = { id: minReg.id, availableAt: date };

    console.log(`minimum registry - id: ${minReg.id}, available At:${new Date(minReg.availableAt).toString()}`);

    res.status(200).send({
        registry_id: minReg.id + 1,
        estimate_start: estimate_start.toTimeString(),
        estimate_end: minReg.availableAt.toTimeString(),
    });
})