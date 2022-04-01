import { Box } from "@chakra-ui/react";
import Head from "next/head";
import axios from "axios";
import Error from "next/error";
import Hero from "../components/Hero";
import BusList from "../components/BusList";

export default function Home({ buses }) {
  if (!buses || buses.length <= 0) {
    return <Error statusCode={500} title={"Stockholm Buses 🚌 is not available currently 😕"} />;
  }

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

      <BusList buses={buses} />
    </Box>
  );
}

export async function getServerSideProps() {
  let buses;

  try {
    const response = await axios({
      method: "GET",
      url: `${process.env.API_URL}/api/buses/top`,
    });

    buses = response.data;
  } catch {
    buses = [];
  }

  return {
    props: {
      buses: buses,
    },
  };
}
