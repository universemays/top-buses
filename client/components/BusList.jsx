import {
  Box,
  Container,
  SimpleGrid,
  Stack,
  Accordion,
  AccordionItem,
  AccordionButton,
  AccordionPanel,
  AccordionIcon,
  HStack,
  Text,
  VStack,
} from "@chakra-ui/react";

export default function BusList({ buses }) {
  return (
    <Container maxW={"6xl"} p={8}>
      <Accordion allowMultiple allowToggle>
        <SimpleGrid columns={{ base: 1, md: 2 }} spacing={6}>
          {buses.map((bus, index) => (
            <Stack key={bus.number}>
              <AccordionItem borderTop="hidden" borderBottom="hidden">
                <Box p={2} shadow="sm" borderWidth="1px" flex="1" borderRadius="lg">
                  <AccordionButton
                    _focus={{ boxShadow: "none" }}
                    _expanded={{ bg: "green.400", color: "white" }}
                    borderRadius="lg"
                  >
                    <HStack>
                      <Text>{index + 1}</Text>
                      <Text>{bus.number}</Text>
                      <Text>{bus.stops.length}</Text>
                    </HStack>
                    <AccordionIcon fontSize={"2xl"} color={"gray.400"} />
                  </AccordionButton>
                  <AccordionPanel pt={4} pb={2}>
                    <VStack>
                      {bus.stops.map((stop, index) => (
                        <HStack key={index + stop.number}>
                          <Text>{stop.name}</Text>
                          <Text>{stop.number}</Text>
                        </HStack>
                      ))}
                    </VStack>
                  </AccordionPanel>
                </Box>
              </AccordionItem>
            </Stack>
          ))}
        </SimpleGrid>
      </Accordion>
    </Container>
  );
}
