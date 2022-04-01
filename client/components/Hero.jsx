import { Box, Container, Heading, Stack, Text } from "@chakra-ui/react";

export default function Hero({ title, subtitle, description }) {
  return (
    <Container px={8} maxW={"3xl"}>
      <Stack
        as={Box}
        textAlign={"center"}
        spacing={{ base: 8, md: 14 }}
        pt={{ base: 20, md: 36 }}
        pb={{ base: 10, md: 20 }}
      >
        <Heading
          fontWeight={600}
          fontSize={{ base: "2xl", sm: "4xl", md: "6xl" }}
          lineHeight={"110%"}
        >
          {title} <br />
          <Text as={"span"} color={"green.400"}>
            {subtitle}
          </Text>
        </Heading>
        <Text color={"gray.500"}>{description}</Text>
      </Stack>
    </Container>
  );
}
