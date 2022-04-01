export default function Home({ buses }) {
  return (
    <div>
      <h1>Top 10 bus lines in Stockholm</h1>
      <p>
        Busses have become popular in Stockholm since 1919. There are around approximately 500 bus
        lines. Among those, these are the 10 top bus lines that have the most bus stops on their
        route.
      </p>
      <div>{JSON.stringify({ buses })}</div>
    </div>
  );
}

export async function getServerSideProps() {
  const buses = [
    {
      number: "1",
      stops: [
        {
          number: "12345",
          name: "Anywhere",
        },
      ],
    },

    {
      number: "2",
      stops: [
        {
          number: "98765",
          name: "Central Station A",
        },
        {
          number: "98766",
          name: "Central Station B",
        },
      ],
    },
  ];

  return {
    props: {
      buses: buses,
    },
  };
}
