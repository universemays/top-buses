import { Box } from "@chakra-ui/react";
import Head from "next/head";
import Hero from "../components/Hero";

export default function Home({ buses }) {
  return (
    <Box>
      <Head>
        <title>Stockholm Busses</title>
        <meta name="description" content="Top 10 Stockholm bus lines" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <Hero
        title={"Top 10 bus lines"}
        subtitle={"in Stockholm"}
        description={
          "Busses have become popular in Stockholm since 1919. There are around approximately 500 bus lines. Among those, these are the 10 top bus lines that have the most bus stops on their route."
        }
      />

      <Box>{JSON.stringify({ buses })}</Box>
    </Box>
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
