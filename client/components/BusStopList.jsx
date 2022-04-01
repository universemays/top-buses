import { Box, Divider, Flex, Spacer, Text, VStack } from "@chakra-ui/react";

export default function BusStopList({ busStops }) {
  return (
    <VStack align={"top"}>
      {busStops.map((stop, index) => (
        <Box key={index + stop.number} flex={"1"}>
          <Flex>
            <Text color={"gray.600"}>{stop.name}</Text>
            <Spacer />
            <Text fontWeight={600}>{stop.number}</Text>
          </Flex>

          {index < busStops.length - 1 && <Divider pt={2} />}
        </Box>
      ))}
    </VStack>
  );
}
