import NextNProgress from "nextjs-progressbar";
import { ChakraProvider } from "@chakra-ui/react";

function MyApp({ Component, pageProps }) {
  return (
    <ChakraProvider>
      <NextNProgress color="#48bb78" />
      <Component {...pageProps} />
    </ChakraProvider>
  );
}

export default MyApp;
